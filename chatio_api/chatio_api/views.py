from django.http import HttpResponse

import json


# Create your views here.
def status(request):
    response = json.dumps({'status': 'RUNNING'})
    return HttpResponse(response, content_type='application/json')
