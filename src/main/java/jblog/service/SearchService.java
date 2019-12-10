package jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jblog.repository.BlogVo;
import jblog.repository.SearchDao;

@Service
public class SearchService {
	@Autowired
	SearchDao searchDao;
	
	public List<BlogVo> searchByTitle(String keyword) {
		List<BlogVo> list = searchDao.selectByTitle(keyword);
		return list;
	}
	
	public List<BlogVo> searchById(String keyword) {
		List<BlogVo> list = searchDao.selectById(keyword);
		return list;
	}
}
