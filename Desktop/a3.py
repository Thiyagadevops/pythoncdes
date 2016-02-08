import sys
import os
try: 
 a=sys.argv[1]
 b=sys.argv[2]
except:
 print 'Exception Raised'

try:
 print "IP Address =",a 
 os.system( "echo 'Hostname = ' `nslookup %s |grep -i name |cut -d= -f2`" %a)
except:
 print 'Exception Raised'
try:
 print "IP Address=",b
 os.system("ct=$(ping -c 1 %s | awk -F, '/received/{print $2*1}'); if [ $ct -eq 1 ]; then echo 'STATUS=UP'; else echo 'STATUS=DOWN'; fi" %b)
except:
 print 'Exception Raised'
 

#subprocess.check_call(['ping','-c1',b])
#status,result = subprocess.getstatusoutput("ping -c 1 " +b)
#proc = subprocess.Popen(["ping -c1 "+b], stdout=subprocess.PIPE).stdout.read()
#proc = subprocess.Popen(["ping -c 1 "+b], stdout=subprocess.PIPE, shell=True)
#(out, err) = proc.communicate()
