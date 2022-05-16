package ejercicios.android.com.ejercicio0908;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;

/**
 *
 */
public class Pulsaciones extends View
{
    float cX = 100; //Coordenadas del círculo
    float cY = 100;
    Paint brocha;   // Brocha para dibujar el círculo.

    // Constructor
    public Pulsaciones(Context context)
    {
        super(context);



        // Inicializamos la brocha, que es una circunferencia
        brocha = new Paint();
        brocha.setColor(ContextCompat.getColor(context, R.color.colorPrimary));
        brocha.setStrokeWidth(5);
        brocha.setStyle(Paint.Style.STROKE);
    }

    // Evento touch del view
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Guardamos las coordenadas del toque
        cX = event.getX();
        cY = event.getY();

        // Invalidamos el view para que se repinte
        this.invalidate();

        return true;
    }

    // Método para el dibujado del círculo
    @Override
    protected void onDraw(Canvas canvas)
    {
        // Dibujamos un círculo en las coordenadas, usando la brocha.
        // Nótese que al repintar se borra lo anterior.
        canvas.drawCircle(cX, cY, 40, brocha);
    }

}
