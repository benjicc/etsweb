
    function Pintardrag(tipos,num){
        
        var tipo = document.getElementById("ctipo"+num.toString());
        console.log(num);
        if(tipos == "imagen"){
            console.log("Putooooooooooo")
        texto= "<select onchange=Pintardrag(this.value,"+num+")><option>-------</option><option value='imagen'>imagen</option><input name='cofield"+num+"' id='cofield"+num+"' type='file'>";
        tipo.innerHTML=texto;
        }else{
            console.log("EEEEEEEEEEEEEEEEEEEEEEEE")
        texto= "<select onchange=Pintardrag(this.value,"+num+")><option>-------</option><option value='imagen'>imagen</option><input name='cofield"+num+"' id='cofield"+num+"' type='text'>";
        tipo.innerHTML=texto;
        }
        
        console.log("eeeeeeeeeeeeeeeeeeeee")
        
    }
    function Pintardrop(tipos, num){
        
        var tipo = document.getElementById("dtipo"+num.toString());
        console.log(tipo.value);
        if(tipos == "imagen"){
            console.log("Putooooooooooo")
        texto= "<select onchange=Pintardrop(this.value,"+num+")><option>-------</option><option value='imagen'>imagen</option></select><input name='dofield"+num+"' id='dofield"+num+"' type='file'>";
        tipo.innerHTML=texto;
        }else{
            console.log("EEEEEEEEEEEEEEEEEEEEEEEE")
        texto= "<select onchange=Pintardrop(this.value,"+num+")><option>-------</option><option value='imagen'>imagen</option></select><input name='dofield"+num+"' id='dofield"+num+"' type='text'>";
        tipo.innerHTML=texto;
        }
        
        console.log("eeeeeeeeeeeeeeeeeeeee")
        
    }
    function Nuevasect(contador){
        var conteo = document.getElementById("conteo");
        console.log(conteo)
        contador = parseInt(conteo.value) + 1;
        conteo.value=contador;
        texto="<tr id='fila"+contador+"'><td id='ctipo"+contador+"'><select name='lctipo"+contador+"' id='lctipo"+contador+"' onchange=Pintardrag(this.value,"+contador+")><option>-------</option><option value='imagen'>imagen</option></select></td><td id='dtipo"+conteo.value+"'><select name='ldtipo"+contador+"' id='ldtipo"+contador+"' onchange=Pintardrop(this.value,"+contador+")><option>-------</option><option value='imagen'>imagen</option></select></td><td><span class='input-group-addon'><button id ='button"+conteo.value+"' name='buttton"+conteo.value+"' class='btn btn-primary  btn-circle' type='button' onclick='Nuevasect("+conteo.value+");'><i id ='id_icono"+conteo.value+"' class='fa fa-plus'></i></button></span></td></tr>";
        $('tbody').append(texto);

        // Obtenemos el penultimo elemento de training phrases
        var contadorAnterior = contador-1;

        // Obtenemos el elemento del DOM del boton y del icono
        var botonAnterior = "button" + contadorAnterior.toString();
        var iconoAnterior = "id_icono" + contadorAnterior.toString();

        // Removemos la clase que tenia el boton "verde" y le colocamos el boton "rojo"
        $(`#${botonAnterior}`).removeClass('btn-primary');
        $(`#${botonAnterior}`).addClass('btn-danger');

        // Removemos la clase que tenia el icono "+" y le colocamos el icono "bote de basura"
        $(`#${iconoAnterior}`).removeClass('fa-plus');
        $(`#${iconoAnterior}`).addClass('fa-trash');

        /*  Le cambiamos el atributo "onclick" al penultimo elemento para que en lugar 
            de al darle click activar la función NuevaFrase active la función EliminarFrase 
        */
        document.getElementById(botonAnterior).setAttribute( "onClick", 'EliminarSect('+contadorAnterior+')');
        
        
    }
    function EliminarSect(contadorBorrar){
        
        var conteo = document.getElementById("conteo");
        console.log("el contadoooor", contadorBorrar);
        conteo.value = parseInt(conteo.value)-1;
        var contador_aux = 1;
        dict={};
            // Obtenemos el div que tenemos que borrar
            idDiv = "fila" + contadorBorrar.toString();
            div = document.getElementById(idDiv);

            //Obtenemos el elemento padre del div que tenemos que borrar y eliminamos a su hijo
            padre = div.parentNode;
            padre.removeChild(div);
            

            /*  Cuando se borra un training phrases tenemos que recorrer los training phrases
                que estan despues de el para recorrer hacia abajo los elementos
            */
           console.log("antesfor");

            for(i=contadorBorrar; i >= contador_aux; i++ ){
            try{     
            console.log("for");
                // Va a ser el indice del elemento que esta despues del que se eliminó
                var anterior = i+1;
                // EL elemento posterior al elemento que se borro pasará a este nuevo indice
                var nuevo = i;
                console.log("for" , anterior);
                //viejos
                actviejo = "activo"+anterior.toString();
                filaidviejo = "fila"+ anterior.toString();
                tiponameviejo= "ctipo"+ anterior.toString();
                tiponameviejo1= "dtipo"+ anterior.toString();
                
                ltiponameviejo= "lctipo"+ anterior.toString();
                ltiponameviejo1= "ldtipo"+ anterior.toString();
                
                botonviejo = "button" + anterior.toString();
                iconoviejo = "id_icono" + anterior.toString();
                opcionviejo = "cofield"+anterior.toString();
                opcionviejo1 = "dofield"+anterior.toString();
                lopcionviejo = "lcofield"+anterior.toString();
                lopcionviejo1 = "ldofield"+anterior.toString();
                //nuevos
                actnuevo = "activo"+nuevo.toString();
                filaidnuevo = "fila"+nuevo.toString();
                tiponamenuevo= "ctipo"+ nuevo.toString();
                tiponamenuevo1= "dtipo"+ nuevo.toString();
                
                ltiponamenuevo= "lctipo"+ nuevo.toString();
                ltiponamenuevo1= "ldtipo"+ nuevo.toString();
                
                botonnuevo = "button" + nuevo.toString();
                icononuevo = "id_icono" + nuevo.toString();
                opcionnuevo = "cofield" + nuevo.toString();
                opcionnuevo1 = "dofield" + nuevo.toString();
                lopcionnuevo = "lcofield" + nuevo.toString();
                lopcionnuevo1 = "ldofield" + nuevo.toString();
                fila = document.getElementById(filaidviejo);
                console.log(fila);
                fila.id = filaidnuevo;
                console.log(tiponamenuevo);
                tipo = document.getElementById(tiponameviejo);
                console.log(tipo);
                tipo.name=tiponamenuevo;
                tipo.id=tiponamenuevo;
                
                tipo1 = document.getElementById(tiponameviejo1);
                console.log(tipo1);
                tipo1.name=tiponamenuevo1;
                tipo1.id=tiponamenuevo1;
                
                ltipo = document.getElementById(ltiponameviejo);
                console.log(tipo);
                ltipo.name=ltiponamenuevo;
                ltipo.id=ltiponamenuevo;
                document.getElementById(ltiponamenuevo).setAttribute( "onchange", 'Pintardrag(this.value,'+nuevo+')');
                ltipo1 = document.getElementById(ltiponameviejo1);
                console.log(tipo1);
                ltipo1.name=ltiponamenuevo1;
                ltipo1.id=ltiponamenuevo1;
                document.getElementById(ltiponamenuevo1).setAttribute( "onchange", 'Pintardrop(this.value,'+nuevo+')');
               
            opcf = document.getElementById(opcionviejo);
                opcf.name= opcionnuevo;
                opcf.id=opcionnuevo;
                
                
                
                opcf1 = document.getElementById(opcionviejo1);
                opcf1.name= opcionnuevo1;
                opcf.id=opcionnuevo1;
                
                lopcf = document.getElementById(lopcionviejo);
                lopcf.name= lopcionnuevo;
                lopcf.id=lopcionnuevo;
                
                lopcf1 = document.getElementById(lopcionviejo1);
                lopcf1.name= lopcionnuevo1;
                lopcf.id=lopcionnuevo1;
            }catch{
                console.log("EEEEEEEEERERERERER/*/*/7*4646465465")
            }
            console.log("LOS BOTONES")
            console.log(botonviejo,botonnuevo)
                boton_modificar2 = document.getElementById(botonviejo);
                boton_modificar2.id=botonnuevo;
                boton_modificar2.name=botonnuevo;
                dict[nuevo] = dict[anterior];
                delete(dict[anterior]);
                console.log(dict);
                if(i < (contador_aux-1)){
                console.log('despues if');
                document.getElementById(botonnuevo).setAttribute( "onClick", 'EliminarSect('+nuevo+')');
                }
                icono_modificar2 = document.getElementById(iconoviejo);
                icono_modificar2.id = icononuevo;
                icono_modificar2.name = icononuevo;
                console.log("*****************************************************")
                document.getElementById(botonnuevo).setAttribute( "onClick", 'Nuevasect('+nuevo+')');
            }
            
    
    }