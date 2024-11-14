package estruturas;

import java.util.Iterator;

public interface EstruturaIF<E> extends Iterable<E> {
    
    public void adicionar(E valor);

    public int tamanho();

    @Override
    public Iterator<E> iterator();
}
