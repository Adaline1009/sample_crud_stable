package com.altarix.crud.service;

import com.altarix.crud.annotation.Loggable;
import com.altarix.crud.aspect.WebServiceLogger;
import com.altarix.crud.model.entity.Doc;
import com.altarix.crud.model.mapper.DocMapper;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Вячеслав on 23.10.2017.
 */
@Service
public class DataServiceImpl implements DataService {

    @Override
    public Doc getById(long id) {
        SqlSession sqlSession = getSession();
        DocMapper docMapper = sqlSession.getMapper(DocMapper.class);
        Doc doc = docMapper.getDocById(id);
        sqlSession.close();
        return doc;
    }

    @Loggable
    @Override
    public void save(Doc doc) {
        SqlSession sqlSession = getSession();
        DocMapper docMapper = sqlSession.getMapper(DocMapper.class);
        docMapper.insert(doc);
        sqlSession.commit();
        sqlSession.close();
    }

    @Loggable
    @Override
    public void remove(Doc doc) {
        SqlSession sqlSession = getSession();
        DocMapper docMapper = sqlSession.getMapper(DocMapper.class);
        docMapper.delete(doc);
        sqlSession.commit();
        sqlSession.close();
    }

    @Loggable
    @Override
    public void update(Doc doc) {
        SqlSession sqlSession = getSession();
        DocMapper docMapper = sqlSession.getMapper(DocMapper.class);
        docMapper.update(doc);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public List<Doc> getAll() {
        SqlSession sqlSession = getSession();
        DocMapper docMapper = sqlSession.getMapper(DocMapper.class);
        List<Doc> docs = docMapper.getDocs();
        sqlSession.close();
        return docs;
    }
}
