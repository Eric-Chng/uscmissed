Server Setup:

Open MySQLWorkbench
Click Server -> Data Import
In Import from Disk tab,
	select import from Self-Contained File
	find the file ServerSetup.sql
	Select option at the bottom: Dump Structure Only
	Click start import

Click Server -> Data Import
In Import from Disk tab,
	select import from Self-Contained File
	find file SampleDataPopulate.sql
	Select option at the bottom: Dump Data Only
	Click start import

Refresh MySQLWorkbench
Server setup done


SERVER INFO:
Schema Name: uscmissed
username and pass depends on your local setup

SERVER DESIGN:
shown in Server Design.png