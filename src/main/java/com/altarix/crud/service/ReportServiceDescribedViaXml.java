package com.altarix.crud.service;

import com.haulmont.yarg.console.ConsoleRunner;
import com.haulmont.yarg.formatters.factory.DefaultFormatterFactory;
import com.haulmont.yarg.loaders.factory.DefaultLoaderFactory;
import com.haulmont.yarg.loaders.impl.SqlDataLoader;
import com.haulmont.yarg.reporting.ReportOutputDocument;
import com.haulmont.yarg.reporting.Reporting;
import com.haulmont.yarg.reporting.RunParams;
import com.haulmont.yarg.structure.Report;
import com.haulmont.yarg.structure.ReportBand;
import com.haulmont.yarg.structure.ReportOutputType;
import com.haulmont.yarg.structure.impl.BandBuilder;
import com.haulmont.yarg.structure.impl.ReportBuilder;
import com.haulmont.yarg.structure.impl.ReportTemplateBuilder;
import com.haulmont.yarg.structure.xml.impl.DefaultXmlReader;
import com.haulmont.yarg.util.db.DatasourceCreator;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Service
public class ReportServiceDescribedViaXml {
    private DataSource dataSource;
    public ByteArrayOutputStream createReport() {
        ByteArrayOutputStream fileByteStream = null;
        dataSource = DatasourceCreator.setupDataSource(
                "org.postgresql.Driver",
                "jdbc:postgresql://localhost:5432/tezistest", "postgres", "11", 10, 10, 0);

        Report report = null;
        try {
            report = new DefaultXmlReader().parseXml(FileUtils.readFileToString(
                    new File("/home/user/IdeaProjects/sample_crud_stable/src/main/resources/templates/documentTemplate.xml")));
        Reporting reporting = new Reporting();

            reporting.setFormatterFactory(new DefaultFormatterFactory());
            reporting.setLoaderFactory(
                    new DefaultLoaderFactory().setSqlDataLoader(new SqlDataLoader(dataSource)));
            fileByteStream = new ByteArrayOutputStream();
            ReportOutputDocument reportOutputDocument = reporting.runReport(
                    new RunParams(report), fileByteStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileByteStream;

    }

}
