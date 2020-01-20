set -x

curl --request GET --header "X-tenant: vw" localhost:8080/cars; echo

curl --request POST --header "X-Tenant: vw" --header "Content-Type: application/json"  --data '{"name":"tiguan","color":"black"}' localhost:8080/cars; echo

curl --request GET --header "X-tenant: vw" localhost:8080/cars; echo

curl --request GET --header "X-tenant: bmw" localhost:8080/cars; echo

curl --request POST --header "X-Tenant: bmw" --header "Content-Type: application/json"  --data '{"name":"X5","color":"orange"}' localhost:8080/cars; echo

curl --request GET --header "X-tenant: bmw" localhost:8080/cars; echo