package liao.practice.springclound02.ribbon;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {
    @Autowired
    private DiscoveryClient client;
    @Autowired
    RestTemplate restTemplate;
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    // @HystrixCommand 该注解对该方法创建了熔断器的功能，并指定了fallbackMethod熔断方法
    @HystrixCommand(fallbackMethod = "hiError")
    public String add() {
        return restTemplate.getForEntity("http://COMPUTE-SERVICE/add?a=10&b=20", String.class).getBody();
    }
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public String info(){
        ServiceInstance instance = client.getLocalServiceInstance();
        return "hi,it's work! --host:" + instance.getHost() + ", service_id:" + instance.getServiceId();
    }
    public String hiError(){
        return "hi,soory request,Hystrix is open";
    }
    @Value("${server.port}")
    String port;
    @RequestMapping("/hi")
    @HystrixCommand(fallbackMethod = "hiError2")
    public String home(@RequestParam String name) {
        return "hi "+name+",i  am lucy and from port:" +port;
    }

    public String hiError2(String name) {
        return "hi,"+name+",sorry,error!";
    }
}