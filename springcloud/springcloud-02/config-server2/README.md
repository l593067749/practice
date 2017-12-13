请求：http://localhost:7001/didispace-dev/from

'''
    {
        "name": "didispace",
        "profiles": [
            "dev[from]"
        ],
        "label": "master",
        "version": "dccb96861945013feb8cac42b53494285adad607",
        "state": null,
        "propertySources": [
            {
                "name": "https://github.com/l593067749/practice/springcloud/springcloud-02/config-repo/didispace.properties",
                "source": {
                    "from": "git-default-1.0"
                }
            }
        ]
    }

'''

http请求地址和资源文件映射如下:

/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties