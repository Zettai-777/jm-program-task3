#!/usr/bin/env bash

mvn clean package

echo 'Copy files...'

scp -i ~/.ssh/id_rsa.pub \
    target/sweater-1.0-SNAPSHOT.jar
    Zettai@Zettai:/home/zettai

echo 'Restart server...'

ssh -i ~/.ssh/id_rsa.pub Zettai@Zettai << EOF

pgrep java |  | xargs kill -9
nohup java -jar sweater-1.0-SNAPSHOT.jar > log.txt &
EOF

echo 'Bye'