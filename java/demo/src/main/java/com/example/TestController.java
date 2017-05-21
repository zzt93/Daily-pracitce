package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by zzt on 5/6/17.
 * <p>
 * <h3></h3>
 */
//@Controller
//@ResponseBody -- without this, return value will be thought as URL of text/html, which cause 404
@RestController
public class TestController {

    /**
     * not working now
     * http://localhost:8080/testRequest?a=1&a=2&b=asdf
     */
    @PostMapping("/testMap")
    public String testMap(@RequestParam(name = "a") Map m, @RequestParam String b) {
        return m.toString();
    }

    /**
     * http://localhost:8080/testMapStr?a=1&a=2&b=asdf
     */
    @GetMapping("/testMapStr")
    public String testMapStr(@RequestParam Map<String, String> m) {
        return m.toString();
    }

    /**
     * http://localhost:8080/testMapStr2?a=1&a=2
     */
    @GetMapping("/testMapStr2")
    public String testMapStr2(@RequestParam Map<String, Integer> m) {
        return m.toString();
    }

    /**
     * http://localhost:8080/testArray?a=1&a=2&b=asdf
     */
    @GetMapping("/testArray")
    public String testArray(@RequestParam(name = "a") String[] strings, @RequestParam String b) {
        return strings.getClass() + "\n" + Arrays.toString(strings);
    }

    /**
     * http://localhost:8080/testArray2?a=1&a=2&b=asdf
     */
    @GetMapping("/testArray2")
    public String testArray2(@RequestParam(name = "a") int[] ints, @RequestParam String b) {
        return ints.getClass() + "\n" + Arrays.toString(ints);
    }

    /**
     * http://localhost:8080/testList?a=1&a=2&b=asdf
     */
    @GetMapping("/testList")
    public String testList(@RequestParam(name = "a") List<String> strings, @RequestParam String b) {
        return strings.getClass() + "\n" + strings.toString();
    }

    /**
     * order not matters
     * more single param works
     * http://localhost:8080/testObj?more=true&page=1&prop1=1&prop2=2&prop3=z
     */
    @GetMapping("/testObj")
    public String testObj(MyObj myObj, @RequestParam(value = "page") int page, @RequestParam boolean more) {
        return myObj.getClass() + "\n" + myObj.toString();
    }

    /**
     * http://localhost:8080/testCustomizedObj?page=1&prop-1=1&prop-2=2&prop-3=z
     */
    @GetMapping("/testCustomizedObj")
    public String testCustomizedObj(@RequestParam int page, MyCustomizedObj obj) {
        return obj.getClass() + "\n" + obj.toString();
    }


}
