#DO NOT CHANGE ANY EXISTING CODE IN THIS FILE
class CoinChange:

	def NumberofCoins(self,denomination,value):
		valp1 = value + 1
		Change =[0 if k == 0 else float("inf") for k in range(valp1)]
		for j in range(len(denomination)):
			for i in range(1, valp1):
				currentDenomination = denomination[j]
				if i >= currentDenomination:
					if Change[i] > 1 + Change[i - currentDenomination]:
						Change[i] = 1 + Change[i - currentDenomination]
		return Change[valp1 - 1]


# # def main():
# # 	c1 = CoinChange()
# # 	ans = c1.NumberofCoins([1,5,10,25],35)
# # 	print ans
#
# if __name__ == '__main__':
# 	main()
