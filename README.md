# Prank bot for RES course

## Description :

The main goal of this project is to be able to send forged e-mails thanks to our PrankBot.

## Instructions for setting up a mock SMTP server (with Docker) : 



Ici simon

Ici simon



Ici simon

Ici simon

Ici simon
Ici simon


Ici simon

Ici simon

Ici simon

Ici simon

Ici simon



Ici simon
Ici simon



Ici simon
Ici simon

Ici simon

Ici simon



Ici simon
Ici simon

## How to :
In order tu run a prank campain, you have set up a few files :
* In **Config/config.properties** you should fill up the different fields ; witnessToCC isn't mandatory, and number of group should be at least a third fo the number of address you want to prank.

* **Config/message.utf8**, should contain all your prank e-mail separated by ---(on a single line). Example are here to guide you.

* **Config/victims.RES.utf8** should be filled with the e-mail addresses of your victims, one per line.

Once it's done, you just have to run the project, and wait for the e-mails to be delivered.

## How it's made :

![ULM Diagramm](/figures/UML.png)

 The project works as follows :
* The ConfigManager gets the datas from the  3 files.
* The PrankGenerator then generates groups and assignes them to Pranks
* Pranks combine a Message, sender witnesses and victims (Person)
* The PrankBot gives the SMTPClient the generated Pranks
* Smtp client communicate with the SMTP server to send mails
