# -*- coding: utf-8 -*-
"""
Created on Sun Feb  4 22:04:43 2018

@author: Jitesh
"""
import tweepy
from tweepy import OAuthHandler
consumer_key = input("ENTER CONSUMER KEY: ")
consumer_secret = input("ENTER CONSUMER SECRET: ")
access_token = input("ENTER ACCESS TOKEN: ")
access_secret = input ("ENTER ACCESS SECRET: ")
 
auth = OAuthHandler(consumer_key, consumer_secret)
auth.set_access_token(access_token, access_secret)
 
api = tweepy.API(auth)

from tweepy import Stream
from tweepy.streaming import StreamListener
 
class MyListener(StreamListener):
 
    def on_data(self, data):
        try:
            with open('python.json', 'a') as f:
                f.write(data)
                return True
        except BaseException as e:
            print("Error on_data: %s" % str(e))
        return True
 
    def on_error(self, status):
        print(status)
        return True
 
twitter_stream = Stream(auth, MyListener())
twitter_stream.filter(track=['microsoft'])
