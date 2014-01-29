<%-- 
    Document   : flexigrid
    Created on : Sep 4, 2012, 11:31:19 AM
    Author     : bruno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <link rel="shortcut icon" type="image/ico" href="http://www.datatables.net/favicon.ico" />
        <title>DataTables example</title>
        <!--        <link rel="stylesheet" type="text/css" href="/css/start/jquery-ui-1.8.21.custom.css" />-->
        <!--        <link rel="stylesheet" type="text/css" href="/css/datatable/demo_table.css" />
                <link rel="stylesheet" type="text/css" href="/css/datatable/demo_page.css" />-->
        <style type="text/css" media="screen">
            @import "/css/site_jui.css";
            @import "/css/demo_table_jui.css";
            @import "/css/jquery-ui-1.7.2.custom.css";
            /*			@import "/css/start/jquery-ui-1.8.21.custom.css";*/

            /*
             * Override styles needed due to the mix of three different CSS sources! For proper examples
             * please see the themes example in the 'Examples' section of this site
            */
            .dataTables_info { padding-top: 0; }
            .dataTables_paginate { padding-top: 0; }
            .css_right { float: right; }
            #example_wrapper .fg-toolbar { font-size: 0.8em }
            #theme_links span { float: left; padding: 2px 10px; }

        </style>
        <script type="text/javascript" language="javascript" src="/js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" language="javascript" src="/js/datatable/jquery.dataTables.js"></script>
        <script type="text/javascript" charset="utf-8">
          
            
            $(document).ready(function() {
                var aSelected = [];
                
                $('#example').dataTable({
                    "sDom": 'R<"H"lfr>t<"F"ip>',
                    "bJQueryUI": true,
                    "sPaginationType": "full_numbers",
                    "bProcessing": true,
                    "bServerSide": true,
                    "sAjaxSource": "/ajax/getSubsWithPagination"
                });
                
                /* Click event handler */
                $('#example tbody tr').live('click', function () {
                    aSelected = [];
                    var id = this.id;
                    var index = jQuery.inArray(id, aSelected);
         
                    if ( index === -1 ) {
                        aSelected.push( id );
                    } else {
                        aSelected.splice( index, 1 );
                    }
         
                    $(this).toggleClass('row_selected');
                } );
            } );
            
    
        </script>
    </head>
    <body id="dt_example">
        <div id="container">
            <div class="full_width big">
                DataTables zero configuration example
            </div>

            <h1>Preamble</h1>
            <p>DataTables has most features enabled by default, so all you need to do to use it with one of your own tables is to call the construction function (as shown below).</p>

            <h1>Live example</h1>
            <span>
                <div id="demo">
                    <table cellpadding="0" cellspacing="0" border="0" class="display" id="example">
                        <thead>
                            <tr>
                                <th width="50%">Rendering engine</th>
                                <th width="50%">Browser</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td colspan="5" class="dataTables_empty">Loading data from server</td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <th>Rendering engine</th>
                                <th>Browser</th>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </span>
        </div>
    </body>
</html>