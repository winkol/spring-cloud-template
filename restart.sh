# spring-boot-*: 项目jar名称
INST_EXIST=$(jps -ml | grep spring-boot-* | wc -l)

IF [[ ${INST_EXIST} -ne 0]]; THEN
	echo "关闭当前 spring-boot-* 实例..."
	jps -ml | grep spring-boot-*
	jps -ml | grep spring-boot-* | awk '{print $1}' | xargs kill -9
fi
# /bankapp/deploy/runtime/jar/spring-boot-*.jar: 绝对jar路径
nohup java -jar /bankapp/deploy/runtime/jar/spring-boot-*.jar --spring.profiles.active=fat >/dev/null 2>&1 &