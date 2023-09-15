
from termcolor import colored

"""
variables:
a -- estudiantes despiertos
c -- estudiantes cansados
s -- estudiantes dormidos
w -- tasa de alumnos despiertos
r -- tasa de somnolencia
"""

def valor_positivo(mensaje): #estudiantes y minutos nunca puede ser negativo
    valor = int(input(mensaje))
    if valor < 0:
        print(f"ERROR: {mensaje} no puede ser negativo.")
        exit()
    return valor

def rango(mensaje): #las tasas tienen que estar en un rango de 0 y 1
    rango = float(input(mensaje))
    if rango > 1 or rango < 0:
         print(f"ERROR: {mensaje} tiene que estar entre 0 y 1")
         exit()
    return rango

a = int(valor_positivo("estudiantes despiertos: "))
c = int(valor_positivo("estudiantes cansados: "))
s = int(valor_positivo("estudiantes dormidos: "))
w = float(rango("tasa de despierte: "))
r = float(rango("tasa de somnolencia: "))
minutos = int(valor_positivo("minutos: "))

print("Estado de los alumnos\n")


for i in range(1, minutos + 1):
    a = a + (w * s) 
    c = c - (r * c * s)
    s = s + (r * c * s) - (w * s)

    total = a + c + s
    total_alumnos = round(a) + round(c) + (round(total) - round(a) - round(c))

    longitud_a = '░' * int(a )
    longitud_c = '░' * int(c )
    longitud_s = '░' * int(s )

    #calcular diferencia y distribuir --> a, c, y s 
    if len(longitud_a) + len(longitud_c) + len(longitud_s) != 47:
        diff = 47 - (len(longitud_a) + len(longitud_c) + len(longitud_s))
        dif_a = int(a * (diff / total_alumnos))
        dif_c = int(c * (diff / total_alumnos))
        dif_s = diff - dif_a - dif_c
        longitud_a += '░' * dif_a
        longitud_c += '░' * dif_c
        longitud_s += '░' * dif_s

    despiertos = colored(longitud_a, color ='light_red')
    cansados = colored(longitud_c, color ='dark_grey')
    durmiendo = colored(longitud_s, color ='cyan')
        
    # verifica y establece a, c y s en 0 si son menores que 0
    a = max(a, 0)
    c = max(c, 0)
    s = max(s, 0)

    print(f'{i:2d}', cansados + durmiendo + despiertos)
    
    