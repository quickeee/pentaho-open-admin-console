@echo off
REM ***************************************
REM   BATCH SCRIPT TO START ADMIN CONSOLE
REM ***************************************
set CLASSPATH=.;resource\config
FOR %%F IN (lib\*.jar) DO call :updateClassPath %%F
FOR %%F IN (jdbc\*.jar) DO call :updateClassPath %%F

goto :startjava

:updateClassPath
set CLASSPATH=%CLASSPATH%;%1
goto :eof

:startjava
call java -Xmx512M -XX:PermSize=64M -XX:MaxPermSize=128M -DCONSOLE_HOME=. -Dlog4j.configuration=resource/config/log4j.xml -cp %CLASSPATH%  org.pentaho.pac.server.JettyServer
