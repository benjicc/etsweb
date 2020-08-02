
            dictdr={};
            function drop(ev) {
                
            ev.preventDefault();
            var data=ev.dataTransfer.getData("text/html");
            /* If you use DOM manipulation functions, their default behaviour it not to 
               copy but to alter and move elements. By appending a ".cloneNode(true)", 
               you will not move the original element, but create a copy. */
            var nodeCopy = document.getElementById(data).cloneNode(true);
            nodeCopy.id = "newId"; /* We cannot use the same ID */
            console.log(data)
            ev.target.appendChild(nodeCopy);
            var n = document.getElementById(nodeCopy.id);
            var s = n.parentNode.nodeName;
            console.log(ev.target.id)
            if(s == "TD"){
                n.remove();
            }else if(s == "IMG"){
                console.log("EEEEEEEEEEEEEEEEEEEEEEEEE");
                n.remove();
            }else if(s == "DIV"){
                console.log("WWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
                n.remove();
            }else if(s == "P"){
                console.log("WWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
                n.remove();
            }else if(s == "NAV"){
            }
            var destino = document.getElementById(ev.target.id);
            try{
                padre = destino.parentNode;
                document.getElementById(ev.target.id).innerHTML = document.getElementById(n.id).innerHTML;

                abuelo = padre.parentNode;
                celdadrop = abuelo.childNodes[1].childNodes[1].childNodes[1].id;
                dictdr[celdadrop]=padre.childNodes[1].childNodes[1].id;
                console.log(celdadrop)
        }catch(o){
            console.log(o)
        }
        try{
            padre = destino.parentNode;
            abuelo = padre.parentNode;
            celdadrop = abuelo.childNodes[3].childNodes[1].childNodes[0].id
            dictdr[celdadrop]=padre.childNodes[1].childNodes[0].id;
            console.log(dictdr)
        }catch(q){
            console.log(q)
        }
            }
        function allowDrop(ev) {
          /* The default handling is to not allow dropping elements. */
          /* Here we allow it by preventing the default behaviour. */
          ev.preventDefault();
        }

        function drag(ev) {
          /* Here is specified what should be dragged. */
          /* This data will be dropped at the place where the mouse button is released */
          /* Here, we want to drag the element itself, so we set it's ID. */
          ev.dataTransfer.setData("text/html", ev.target.id);
        }


