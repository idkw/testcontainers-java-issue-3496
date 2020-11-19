# Description

This is a reproducer for issue https://github.com/testcontainers/testcontainers-java/issues/3496

There are two main classes

One with a docker-compose container declared and started inside the main method called `MainNoBug`

Another one with the same docker-compose container but declared in a static field 
and started inside the main method caled `MainBug`.

In both cases, the app starts, start the container, wait a few seconds 
to let you type `docker network ls` then terminates.


With `MainNoBug` the containers and networks are properly cleand up when the app terminates.
With `MainBug` the containers are cleaned up but the network remains.

The expectation is that with `MainBug` the network should also be cleaned up as with `MainNoBug`




