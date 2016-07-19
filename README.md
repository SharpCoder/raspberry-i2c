# raspberry-i2c
A project to demonstrate how to do i2c using java, as well as some drivers that I have written. This project is nothing fancy, just an example of one way to do i2c. It's also admittedly not terribly good because it shells everything to the "i2c-tools" utility that can easily be installed to the raspberry pi. Here's a good guide from the folks at sparkfun: https://learn.sparkfun.com/tutorials/raspberry-pi-spi-and-i2c-tutorial#i2c-on-pi (thanks sparkfun!)

# What drivers are available?
I have a side project going on right now, and have written a few "drivers" using this system. As I add more to my personal project, I will be sure to include them here. Right now, the following are supported:

 * A3967 - Or something like it. This is a motor controller breakout board.
 * AA256 - More properly known as 24AA256, an eeprom chip available on sparkfun.
 * HMC5883L - Please note **this is completely broken right now**. But I'll be fixing it soon. It's basically a placeholder until then. But the chip is actually a compass module.
 * MCP230XX - This is a GPIO expander chip. I've tested it, notably, with the MCP23017 and MCP23008.

# Why shell to the i2c-tools?
This is twofold. The most important being that you can much easier tell whats going on (for learning purposes). Consider adding a flag to print the output of the send/receive commands for further bit-banging.

* **Wouldn't that be slow?** - You'd think. But it's really not bad at all.
* **How can I do this properly?** - I believe it's a matter of writing things to the /dev/i2c-1 "file" though I haven't fully looked at the iotcl library to comprehend what's involved. Should be pretty reasonable. But again, I like the simplicity of this method for learning.


# Will this project be maintained?
Yes! Well, to some extent. I plan to add more comments in the near future to help aspiring i2c driver writers understand the nuances of what is going on. Also, I intend to add all other device drivers that I create to this project. I've done extensive raspberry-pi work before and it's nice to contribute to the community. So I promise to keep this up to date as I continue to learn more and write more drivers.