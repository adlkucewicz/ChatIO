from django.http import HttpResponse

import json


# Create your views here.
def status(request):
    print('Hello')
    response = json.dumps({'status': 'RUNNING'})
    return HttpResponse(response, content_type='application/json')
