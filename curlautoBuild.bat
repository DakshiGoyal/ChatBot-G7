
cd C:\Curl\src
curl -v http://localhost:8080/api/questions/1

curl -d "{\"userName\":\"Deepak\",\"contactno\":9971986594,\"email\":\"deepak@gmail.com\",\"city\":\"chanda\"}" -H "Content-Type: application/json" http://localhost:8080/api/users

curl -v http://localHost:8080/api/questions/2/"1"


curl -d "{\"deviceName\":\"IntelliVue750\",\"touch\":\"touch\",\"screenSize\":10}" -H "Content-Type: application/json" http://localhost:8080/api/devices

curl -d "{\"userAnswer\":[\"nontouch\",\"12\"]}" -H "Content-Type: application/json" http://localhost:8080/api/getDevices

pause