#!/bin/sh

cd `dirname $0`

classpath=app/
for file in jars/*.jar; do
    classpath=$classpath:$file
done

java -cp $classpath clojure.lang.Script app/boot.clj
