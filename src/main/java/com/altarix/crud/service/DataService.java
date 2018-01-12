package com.altarix.crud.service;

import com.altarix.crud.model.entity.Doc;
import com.altarix.crud.model.mapper.DocMapper;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;

import java.util.List;

/**
 * Created by Вячеслав on 23.10.2017.
 */
public interface DataService {

    default SqlSession getSession() {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(new PooledDataSource("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/tezistest", "postgres", "11"));
        SqlSessionFactory sessionFactory;
        SqlSession sqlSession = null;
        try {
            sessionFactory = factoryBean.getObject();
            sessionFactory.getConfiguration().addMapper(DocMapper.class);
            sqlSession = sessionFactory.openSession(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sqlSession;
    }

    void save(Doc doc);

    void remove(Doc doc);

    void update(Doc doc);

    Doc getById(long id);

    List<Doc> getAll();
}
