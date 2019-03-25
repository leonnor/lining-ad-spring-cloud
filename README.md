# lining-ad-spring-cloud
## 基于SpringCloud的广告系统 
## Advertising system based on SpringCloud

### 各模块介绍
### Introduction of each module

#### ad-eureka
    服务发现与服务注册
    Service discovery and Service registration
#### ad-gateway
    zuul网关，实现路由转发与请求信息记录
    zuul gateway,implementing routing forwarding and recording request messages
#### ad-common
    通用模块，主要包括：通用代码定义、配置定义，统一的响应处理和统一的异常处理
    common module mainly include general code definition, configuration definition, unified response processing and unified exception processing
#### ad-sponsor
    广告系统子模块：广告投放系统
    Submodule of Advertising System: Advertising Playing System
    广告投放系统已完成
    Advertising delivery system completed
#### ad-search
    广告系统子模块：广告检索系统
    Submodule of Advertising System: Advertising Searching System
    主要功能：提供给请求方（媒体方）根据请求信息返回对应的创意信息