echo ****************** Reset docker container ******************
docker-compose -f ./docker-compose.yml down

echo ****************** Remove Data ******************
rm -rf ./data
echo ****************** Completed Remove Data******************

docker-compose -f ./docker-compose.yml up
