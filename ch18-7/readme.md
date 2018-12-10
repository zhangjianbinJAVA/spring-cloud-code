### 网关动态路由，单机版，route 信息保存与内存中

### 查询路由：
http://127.0.0.1:8080/actuator/gateway/routes

### 添加路由：
http://127.0.0.1:8080/route/add
````
{
	"filters": [],
	"id": "jd_route",
	"order": 0,
	"predicates": [{
		"args": {
			"pattern": "/jd"
		},
		"name": "Path"
	}],
	"uri": "http://www.jd.com"
}
````


### 更新路由：
http://127.0.0.1:8080/route/update


#### 网关动态路由：集群版：实现 RouteDefinitionRepository 类，配置信息从数据库或配置中心获取