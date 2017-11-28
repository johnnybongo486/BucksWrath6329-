# import the necessary packages
from __future__ import print_function
import cv2
import numpy
import sys
import math
import time
from networktables import NetworkTable

from picamera.array import PiRGBArray
from picamera import PiCamera
import piVision

#LOWER_HSV = numpy.array([43,113,130])
#UPPER_HSV = numpy.array([99,255,255])
LOWER_HSV = numpy.array([50,0,252])
UPPER_HSV = numpy.array([100,20,255])
IMG_WIDTH = 640
IMG_HEIGHT = 480
IMG_CENTER_ERROR = 0
kernel = numpy.ones((3,3),numpy.uint8)
GEAR_STATE = 0
BOILER_STATE = 1

# Capture Video
video = piVision.PiVideoStream((IMG_WIDTH,IMG_HEIGHT)).start()
time.sleep(2)  # Give camera a chance to stablize

# Read Test Image
# image = cv2.imread("Test_IMG2.png")

# Connect to roboRio via NetworkTable
# NetworkTable.setIPAddress("10.63.29.2")
# NetworkTable.setClientMode()
# NetworkTable.initialize()
# table = NetworkTable.getTable("visionTracking")

def dst2errorX(TargetCenter, Distance) :
    theta = math.atan((TargetCenter-IMG_CENTER_ERROR)/622.7722)
    return Distance*math.sin(theta)

# Convert video to image
image = video.read()

img_hsv = cv2.cvtColor(image, cv2.COLOR_BGR2HSV)
cv2.imshow("img_hsv",img_hsv)

# Use the HSV threshold
img_thresh = cv2.inRange(img_hsv, LOWER_HSV, UPPER_HSV)
cv2.imshow("img_thresh",img_thresh)

# Dialata the image
img_dialate = cv2.dilate(img_thresh, kernel, iterations=1)
cv2.imshow("img_dialate",img_dialate)

# Find the contours
_, contours, _ = cv2.findContours(img_dialate, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)

cv2.drawContours(image,contours,-1,(0,255,0),3)
cv2.imshow("Output", image)

turn = 0
okCount = 0
j = 0
OkCountours = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
if len(contours) > 0:
    #Check all contours, or until we see one that looks like a goal
    for c in contours:
        # Is the contour big enough to be a goal?
        area = cv2.contourArea(c) # area of rectangle that is filled
        x,y,w,h = cv2.boundingRect(c)
        ratio = float(h) / float(w)
        ratioError = ratio-5.0/2.0
        if abs(ratioError) < 1.5 and  w*h > 100:
            center = x + (w/2)
            # print h,w,area,center,turn
            if okCount > 11:
                print("Too much contours")
                break

            OkCountours[okCount] = j
            okCount = okCount + 1
        j = j + 1
else:
    print ("No contours found")
    turn = 0
# print "RatioOkCount:",okCount
i = 0
markercount = 0
Yr = [0,0]
Xr = [0,0]
slideRate = 0
pixAngle = 0
while  okCount > i+1:
    x,y,w,h = cv2.boundingRect(contours[OkCountours[i]])
    j = 0
    while i+j < okCount :
        x_p,y_p,w_p,h_p = cv2.boundingRect(contours[OkCountours[i+j]])
        if abs(abs(float(x - x_p) / ((h+h_p)/2.0))-(8.25/5.0)) < 1.0 and abs(y_p-y) < 15 and abs(h-h_p) < 10  :
                # calculate each value
                dist = [5*697.2/h, 5*697.2/h_p] #calculate distance based upon height of marker
                # Robot position on X axis
                Xr = [dst2errorX((x+(w/2.0))-IMG_WIDTH/2, dist[0]),dst2errorX((x_p+(w_p/2.0))-IMG_WIDTH/2, dist[1])]
                CenterX = [x+(w/2)-(IMG_WIDTH/2), x_p+(w_p/2)-(IMG_WIDTH/2)]
                # Robot position on Y axis
                Yr = [math.sqrt(dist[0]*dist[0]-Xr[0]*Xr[0]),math.sqrt(dist[1]*dist[1]-Xr[1]*Xr[1])]
                # Robot angle to the target in radian
                # angle = math.atan((Yr[1]-Yr[0])/(Xr[1]-Xr[0]))
                # pixel difference of 2 markers in pixels
                # pixAngle = abs(h-h_p)
                # if angle < 0 :
                #    pixAngle = -pixAngle
                # if(abs(pixAngle) > 5) :
                #    pixAngle = 5*pixAngle/abs(pixAngle)
                slideRate = (CenterX[0]+CenterX[1])/2.0
                markercount = markercount+1

                #show the value
                #print ((x+(w/2.0))-IMG_WIDTH/2)
                print (slideRate)
                cv2.circle(image,(int((x+x_p+(w_p+w)/2.0)/2.0),int(((y+y_p)/2.0)+(h+h_p)/4.0)),3,(0,0,255), -1)
                cv2.rectangle(image,(x,y),(x+w,y+h),(255,0,0),3) # draw rectangle in blue color)
                cv2.rectangle(image,(x_p,y_p),(x_p+w_p,y_p+h_p),(255,0,0),3)
                cv2.imshow("Rectangle", image)
                # break


        j = j + 1
    i = i + 1

if markercount == 1 :
    table.putNumber("distance", (Yr[0]+Yr[1])/2.0)
    table.putNumber("slideRate", slideRate)
else :
    table.putNumber("distance", 704)
    table.putNumber("slideRate", 704)
cv2.waitKey(0)
#   table.putNumber("rateTurn" , 704)
#key = cv2.waitKey(20)
#if key == 27: # exit on ESC
#break
# cv2.destroyAllWindows()
