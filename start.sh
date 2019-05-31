nohup java -jar 项目包名.jar --spring.profiles.active=prod >> /home/xxx/logs/xxx.log 2>&1 &
sleep 10
echo "start ok"
exit 0