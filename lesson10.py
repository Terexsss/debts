import pandas as pd
import random

# Генерация исходных данных
lst = ['robot'] * 10
lst += ['human'] * 10
random.shuffle(lst)
data = pd.DataFrame({'whoAmI': lst})

# Печать исходного DataFrame
print("Исходный DataFrame:")
print(data.head())

# Получение уникальных значений столбца
unique_values = data['whoAmI'].unique()

# Создание пустого DataFrame для one-hot кодирования
one_hot_df = pd.DataFrame()

# One-hot кодирование
for value in unique_values:
    one_hot_df[value] = (data['whoAmI'] == value).astype(int)

# Печать результирующего DataFrame
print("\nOne-hot кодированный DataFrame:")
print(one_hot_df.head())