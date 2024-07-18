package com.htjs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class TestController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("setKey")
    public String setKey() {
        for(int i = 0; i < 10; i++) {
            stringRedisTemplate.opsForValue().set("random" + i, "" + i);
        }
        return "success";
    }

}
