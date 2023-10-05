/*Enunciado
Nuestra consultora, San Andrés Robotics y su exitoso desarrollo del Mars Rover, llamó la atención de la sección de investigación marina del mayor contratista de software del mundo, el DoD. 
Nos confiaron su proyecto más mimado, Nemo. 
Nemo es un sumergible autónomo con la capacidad de navegar en superficie o sumergido, que incluye la capacidad de lanzar en forma vertical unas cápsulas de otro proyecto más mimado aún, pero secreto, sólo nos comentaron su nombre, Incredible Chocolate Brownie Moment que por suerte suena por demás inofensivo. 

A Nemo se lo controla por un canal incorruptible por el que se le envían uno o más comandos prepautados. 
Los comandos son: 
- 'd' que le indica a Nemo descender una unidad. 
- 'u' que le indica a Nemo ascender una unidad. 
- 'l' que le indica a Nemo rotar 90 grados a izquierda. 
- 'r' que le indica a Nemo rotar 90 grados a derecha. 
- 'f' que le indica a Nemo avanzar una unidad. 
- 'm' que le indica a Nemo liberar la cápsula. 

Nos toca desarrollar el sistema de posicionamiento de Nemo, para eso se nos pide la capacidad de procesar cada comando y mantener actualizada la actitud del sumergible en un sistema cartesiano. 
El DoD es muy exigente en sus estándares de desarrollo y nos pide por contrato un desarrollo usando la técnica de TDD, con una completa cobertura con casos de test. Se ve que entienden del tema! 
Les preocupa mucho el código repetido, el uso de Ifs, los malos nombres y las responsabilidades entre objetos mal repartidas (*).

Algunas características del submarino que tenemos que controlar son que: por ahora no hay problemas con sumergirse demasiado, tampoco pasa nada si le insistimos en emerger cuando está en superficie. Por otro lado, la cápsula sólo se puede lanzar en superficie o en el primer nivel de inmersión. En estos casos lanzar la cápsula no tiene ningún efecto detectable en el submarino. Pero si se intenta liberar a mayor profundidad de lo permitido el submarino se destruye, probablemente por exceso de chocolate.

(*) comentaron algo como que incluye todo lo visto y corregido hasta ahora, porque se va a evaluar también ¯\_(ツ)_/¯*/

package SubmarineProject;

/**
 * nemo
 */
public class Nemo {
    private int x;
    private int y;
    private int z;
    private int capsuleDepthLimit;
    private boolean isSurface;
    private boolean isDestroyed;

    public Nemo() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.capsuleDepthLimit = 1;
        this.isSurface = true;
        this.isDestroyed = false;
    }

    public void processCommand(char command) {
        if (isDestroyed) {
            return;
        }

        switch (command) {
            case 'd':
                descend();
                break;
            case 'u':
                ascend();
                break;
            case 'l':
                rotateLeft();
                break;
            case 'r':
                rotateRight();
                break;
            case 'f':
                moveForward();
                break;
            case 'm':
                launchCapsule();
                break;
            default:
                break;
        }
    }

    private void descend() {
        z--;
    }

    private void ascend() {
        z++;
    }

    private void rotateLeft() {
        if (isSurface) {
            x--;
        } else {
            y--;
        }
    }

    private void rotateRight() {
        if (isSurface) {
            x++;
        } else {
            y++;
        }
    }

    private void moveForward() {
        if (isSurface) {
            y++;
        } else {
            x++;
        }
    }

    private void launchCapsule() {
        if (isSurface || z == capsuleDepthLimit) {;
            return;
        }

        isDestroyed = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public boolean isSurface() {
        return isSurface;
    }

    public void setSurface(boolean surface) {
        isSurface = surface;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setCapsuleDepthLimit(int capsuleDepthLimit) {
        this.capsuleDepthLimit = capsuleDepthLimit;
    }
}