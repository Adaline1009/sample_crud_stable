package com.altarix.crud;

import com.altarix.crud.service.ReportService;
import com.altarix.crud.service.ReportServiceDescribedViaXml;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.io.IOException;

@SpringBootApplication
@MapperScan("mapper")
public class CrudApplication {
	public static void main(String[] args) {
		ApplicationContext ctx =SpringApplication.run(CrudApplication.class, args);

			ReportService reportService = ctx.getBean(ReportService.class);
			//reportService.createReport();
		ReportServiceDescribedViaXml reportServiceXml =ctx.getBean(ReportServiceDescribedViaXml.class);
		reportServiceXml.createReport();


	}
}
