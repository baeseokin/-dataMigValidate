echo off
set arg1=%1
set arg2=%2

java -jar ./target/dataMigValidate-0.0.1-SNAPSHOT.jar --job.name=%arg1% 