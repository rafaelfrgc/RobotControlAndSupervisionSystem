@echo off
:: This script is used to run SBGTest program to test Java SBG dynamic
:: library on Windows systems.
:: You can specify host processor architecture (32 or 64 (default)),
:: followed by SBGTest arguments.
:: Example: sbgtest 32 COM1

setlocal

set libdir=.

:: Set 32 or 64 bit arquitecture
if [%1] EQU [32] (
  :: Compile using 32 bits
  set archBits=32
  shift /1
) else if [%1] EQU [64] (
  :: Compile using 64 bits
  set archBits=64
  shift /1
) else (
  :: Compile using 64 bits (default)
  set archBits=64
)

set sbglibdir=%libdir%\w%archBits%


echo Executing SBG Device Java test on %archBits% architecture...
java -d%archBits% -cp . -Djava.library.path=%sbglibdir% SBGTest %1 %2 %3
pause


