$(document).ready(function() {
    $('#generarPresupuestoForm').submit(function(e) {
        e.preventDefault();

        var productosSeleccionados = [];
        $('input[name="productos"]:checked').each(function() {
            productosSeleccionados.push($(this).val());
        });

        $.ajax({
            url: '/presupuesto/generar',
            type: 'POST',
            data: JSON.stringify(productosSeleccionados),
            contentType: 'application/json',
            xhrFields: {
                responseType: 'blob' // Establece el tipo de respuesta a Blob
            },
            success: function(response) {
                // Crear una URL para el objeto Blob
                var url = window.URL.createObjectURL(new Blob([response]));

                // Crear un elemento <a> para descargar el archivo
                var link = document.createElement('a');
                link.href = url;
                link.setAttribute('download', 'Presupuesto.pdf');
                link.style.display = 'none';

                // Agregar el elemento <a> al documento y simular un clic para descargar el archivo
                document.body.appendChild(link);
                link.click();

                // Limpiar y revocar la URL
                window.URL.revokeObjectURL(url);
                document.body.removeChild(link);
            },
            error: function(xhr, status, error) {
                console.error(xhr.responseText);
            }
        });
    });
});
