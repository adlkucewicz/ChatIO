import datetime

from django.shortcuts import render

# Create your views here.
def index(request): #represents http request user makes
    now = datetime.datetime.now()

    return render(request, "hello/index.html", {
        "datetime": now
    })

