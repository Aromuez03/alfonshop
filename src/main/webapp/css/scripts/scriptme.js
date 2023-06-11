function mostrarAlerta(mensaje) {
  var alerta = document.createElement('div');
  alerta.className = 'alerta-error';
  alerta.textContent = mensaje;
  
  document.body.appendChild(alerta);
  
  setTimeout(function() {
    alerta.style.opacity = '0';
    setTimeout(function() {
      document.body.removeChild(alerta);
    }, 1000);
  }, 3000);
}
