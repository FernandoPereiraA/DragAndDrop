package ejercicio0909;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;

/**
 *
 */
public class Lienzo extends View {
    Paint brocha;     // Brocha para dibujar las líneas
    float grosor = 6; // Grosor de la línea
    Path[] paths;     // Array con todos los paths
    int ultimoPath;   // Índice del último path que estamos dibujando

    // Constructor
    public Lienzo(Context context)
    {
        super(context);


        // Inicializamos el array y el índice
        paths = new Path[1000];
        ultimoPath = -1;

        brocha = new Paint();
        brocha.setColor(ContextCompat.getColor(context, R.color.colorAccent));
        brocha.setStrokeWidth(grosor);
        brocha.setStyle(Paint.Style.STROKE);
    }

    // Evento touch
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        // Coordenadas pulsadas
        float cX = event.getX();
        float cY = event.getY();

        // Comprobamos qué acción es. Si es MotionEvent.ACTION_DOWN, iniciamos el path con moveTo().
        // Por cada vez que recibimos la acción MotionEvent.ACTION_MOVE añadimos un punto al path.
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                // Empezamos una nueva línea en el array
                ultimoPath++;
                paths[ultimoPath] = new Path();
                paths[ultimoPath].moveTo(cX, cY);
                break;

            case MotionEvent.ACTION_MOVE:
                // Añadimos un punto al path, trazando una línea hasta él.
                paths[ultimoPath].lineTo(cX, cY);
                break;
        }

        //Toast.makeText(getContext(), "dsa", Toast.LENGTH_LONG).show();

        // Invalidamos el view para que repinte.
        this.invalidate();

        return true;
    }

    // Método para el dibujado
    @Override
    protected void onDraw(Canvas canvas)
    {
        // Tenemos que dibujar todas las líneas previas ya que onDraw repinta tod el canvas
        for (Path linea : paths)
        {
            if (linea != null) {
                canvas.drawPath(linea, brocha);
            }
        }
    }
}
