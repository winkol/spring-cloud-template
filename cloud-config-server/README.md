# springCloud config-server 远程配置中心服务

# 读取远程配置
spring:
  cloud:
    config:
      server:
        git:
          # 配置git仓库地址
          uri: git@github.com:winkol/myspringcloudconfig.git
          # 配置仓库路径
          search-paths: myspringcloudconfig
          # 访问git仓库的用户名
          username: winkol
          # 访问git仓库的用户密码 如果Git仓库为公开仓库，可以不填写用户名和密码，如果是私有仓库需要填写
          password: Z123123a
          #更改本地仓库clone的配置文件信息的路径
          basedir: E:\\ld_work\\workspaces\\ideaIU\\spring-cloud\\myspringcloudconfig

        #配置中心服务端健康监测器
        health:
          #开启或者关闭健康监测功能，默认是true开启状态
        #  enabled: true

          repositories:
            check:
              name: config-client
              label: master
              profiles: dev

      # 配置仓库的分支
      label: master

# 读取本地配置
1：config读取本地配置,不同环境使用 include
2：或者使用 active: dev,native
spring:
  profiles:
    active: dev,native
    include: dev
    
 本地配置文件路径
spring:
  cloud:
    config:
      native:
        searchLocations: file:./config

