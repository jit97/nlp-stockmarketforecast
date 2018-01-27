import datetime as dt
import matplotlib.pyplot as plt
from matplotlib import style
import pandas as pd
import pandas_datareader.data as web
style.use('ggplot')
start = dt.datetime(2017, 1, 1)
end = dt.datetime(2018, 1, 27)
df = web.DataReader('AAPL', "yahoo", start, end)
print(df.head())
df['Adj Close'].plot()
plt.show()

 
