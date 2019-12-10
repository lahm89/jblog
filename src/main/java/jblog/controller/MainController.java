package jblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jblog.repository.BlogVo;
import jblog.service.SearchService;

@Controller
public class MainController {
	@Autowired
	SearchService searchService;
	
	@RequestMapping("/")
	public String mainPage() {
		return "main";
	}
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String searchBlog(@RequestParam(value="keyword") String keyword, @RequestParam(value="keywordType") String keywordType, Model model) {
		List<BlogVo> list;
		if(keywordType.equals("id")) {
			System.err.println("검색시작");
			list = searchService.searchById(keyword);
		} else {
			System.err.println("검색시작" + keyword);
			list = searchService.searchByTitle(keyword);
		}
		model.addAttribute("list", list);
		model.addAttribute("keyword", keyword);
		return "includes/searchresult";
	}

}
