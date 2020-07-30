FROM openjdk:16-slim

COPY ./target /

CMD java -jar poker-hand-evaluator.jar $INPUT