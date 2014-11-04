import web, json
from bootstrap import *
urls = (
    '/rpi/', 'index'
)

class index:
    def GET(self):
        return "Hello World!"
    def POST(self):
        data = web.data()
        decode = json.loads(data)
	lightlist = {}
	ids = []
	for key, value in decode.iteritems():
		id = ''
		if key == 'lights':
			for x in range (0,len(value)):
				id = value[x]['lightId']
				ids.append(id)
				r = value[x]['red']
				b = value[x]['blue']
				g = value[x]['green']
				i = value[x]['intensity']
				lightlist[id] = [r, b, g, i]
				print lightlist
			#led.fill(Color(lightlist.get(x)[0], lightlist.get(x)[2], lightlist.get(x)[1], lightlist.get(x)[3]), x)
			led.update()		 

if __name__ == "__main__":
    app = web.application(urls, globals())
    app.run()
