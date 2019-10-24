package com.sample.app;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sample.dao.BaseMapper;

@Controller
public class TestController {

    @Autowired
    private SqlSession sqlSession;
    
    @Autowired
    private BaseMapper baseMapper;
    
    @RequestMapping("/page01")
    public String getPage01(Model model) {
        
        String returnValue = sqlSession.selectOne("com.sample.dao.BaseMapper.selectSample", "TEST");
        model.addAttribute("frm", "page01");
        model.addAttribute("msg", returnValue);
        
        return "sample";
    }
    
    @RequestMapping("/page02")
    public String getPage02(Model model) {
        
        String returnValue = baseMapper.selectSample();
        model.addAttribute("frm", "page02");
        model.addAttribute("msg", returnValue);
        
        return "sample";
    }
}
