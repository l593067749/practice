package liao.practice.springclound02.eurekaclient;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ComputeController {
    private final Logger logger = Logger.getLogger(getClass());
    @Autowired
    private DiscoveryClient client;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/add" ,method = RequestMethod.GET)
    public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
        ServiceInstance instance = client.getLocalServiceInstance();
        Integer r = a + b;
        logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);
        return r;
    }
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public String info(){
        ServiceInstance instance = client.getLocalServiceInstance();
        return "hi,it's work! --host:" + instance.getHost() + ", service_id:" + instance.getServiceId();
    }
    @RequestMapping(value = "/zkTest",method = RequestMethod.GET)
    public String zipkinTest(@RequestParam(defaultValue = "3") int b){
        return restTemplate.getForObject("http://localhost:2223/add?a=3&b="+b,String.class);
    }
}