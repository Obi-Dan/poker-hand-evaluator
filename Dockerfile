FROM openjdk:16-slim

COPY ./target /

CMD java -jar poker-hand-evaluator.jar $INPUT

# docker build -t danshoff/poker-hand-evaluator .
# docker run -it --rm -e INPUT="Chinese1.txt" danshoff/poker-hand-evaluator