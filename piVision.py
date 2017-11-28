from picamera.array import PiRGBArray
from picamera import PiCamera
from threading import Thread
import cv2
import time

class PiVideoStream:
    def __init__(self, resolution=(1280, 720), format = "bgr", framerate=30):
        # initialize the camera and stream
        self.camera = PiCamera()
        time.sleep(.2) #let camera init
        self.camera.resolution = resolution
        self.camera.framerate = framerate
        self.camera.framerate = 30
        self.camera.sharpness = -100
        self.camera.contrast = 50
        self.camera.saturation = 100
        self.camera.brightness = 35
        self.camera.awb_mode = 'off'
        self.camera.awb_gains = (1.29, 1.44)
        self.camera.shutter_speed = 800
        self.camera.exposure_mode = 'night'
        self.rawCapture = PiRGBArray(self.camera, size=resolution)
        self.stream = self.camera.capture_continuous(self.rawCapture,
            format=format, use_video_port=True)
        time.sleep(.2) #let camera init

        # initialize the frame and the variable used to indicate
        # if the thread should be stopped
        self.frame = None
        self.stopped = False

    def start(self):
        # start the thread to read frames from the video stream
        Thread(target=self.update, args=()).start()
        return self

    def update(self):
        # keep looping infinitely until the thread is stopped
        for f in self.stream:
            # grab the frame from the stream and clear the stream in
            # preparation for the next frame
            self.frame = f.array
            self.rawCapture.truncate(0)

            # if the thread indicator variable is set, stop the thread
            # and resource camera resources
            if self.stopped:
                self.stream.close()
                self.rawCapture.close()
                self.camera.close()
                return

    def read(self):
        # return the frame most recently read
        return self.frame

    def stop(self):
        # flag thread to stop
self.stopped = True
