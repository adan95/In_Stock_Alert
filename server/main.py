import flask
from flask import request
from bs4 import BeautifulSoup
import pandas as pd
import requests

import sys

import socket
from time import sleep


server = flask.Flask(__name__)

hostname = socket.gethostname()
local_ip = socket.gethostbyname(hostname)

# Setting up query search request
locations = ["losangeles"]
keyword = "3070"
# What would be cool is to create a search dictionary -----> set up code that finds word associated with that key search word
page = requests.get("https://" + locations[0] + ".craigslist.org/d/for-sale/search/sss?query=" + keyword)
# Need to edit to work when error with url
print("https://" + locations[0] + ".craigslist.org/d/for-sale/search/sss?query=" + keyword)
try:
    print(page.status_code)
except:
    print("error occured")  # """DNW needs fixing"""
soup = BeautifulSoup(page.content, 'html.parser')

# Create search-dictionary
all_products = {"name": [], "price": [], "date": [], "url": [], "location": []}

# Extract and store in top_items according to instructions on the left
products = soup.select('li.result-row')
count = 0
for product in products:
    name = product.select("h3 > a")[0].text.strip()
    price = product.select("span.result-price")[0].text.strip()
    date = product.select("time.result-date")[0].text.strip()
    url = product.select("h3.result-heading > a")[0].get("href")

    # Needs work to filter unwanted results it varies and sometime the location is not in the html span> tag
    location = product.select("span.result-meta")[0].find_all("span")[1].text.strip()

    # REVIST
    # probobly going to have to open up the link and go to the page then extract the image from the link
    # img = products[0].select("li.result-row>a")[0].get('src')
    # img = products[0].select("li.result-row>a")[0].get("href")
    # url = product.select("h3.result-heading > a")[0].get("href")

    all_products["name"].append(name)
    all_products["price"].append(price)
    all_products["date"].append(date)
    all_products["url"].append(url)
    all_products["location"].append(location)

df = pd.DataFrame(data=all_products)
info = df.to_json()


@server.route('/', methods=["GET"])
def home():
    msg = "look for things, well you at the right server --->  "
    return msg + "add '/in_stock' to the url to get back json data of 3070 in lA stock"


@server.route('/in_stock', methods=['GET'])
def in_stock():
    return info



