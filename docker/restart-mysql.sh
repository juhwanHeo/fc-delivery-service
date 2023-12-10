echo "****************** Reset docker container ******************"
docker-compose down

sleep 10
echo "****************** Remove Data ******************"
rm -rf ./mysql/data
echo "****************** Completed Remove Data******************"

docker-compose up
