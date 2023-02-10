/** @jsxImportSource @emotion/react */
import React, { useEffect, useState } from 'react';
import { getServices, GetServicesResponse } from '../api/services';
import { ListServices } from './ListServices';
import { AddModal } from './AddModal';

import { Input, InputLabel, Button } from '@mui/material';
export const SearchServices = () => {
    const [rows, setRows] = useState<Array<GetServicesResponse>>([]);

    const getServiceList = async() => {
        await getServices().then((data) => {
          setRows(data)
        }
        )
      }

  useEffect(() => {
    getServiceList();
  }, []);
  return (
    <div>
        <div className="panel-heading">
            <h3 className="panel-title">Service Search</h3>
        </div>

        <div  className="rcorners2 row">
            <div className="col-sm-3">
            <InputLabel children="Service Name"></InputLabel>
            <Input name="serviceName" placeholder='permission-api'/>
            </div>
            <div className="col-sm-3">
            <InputLabel children="Service ID"></InputLabel>
            <Input name="serviceId" placeholder='3456'/>
            </div>
            
            <div className="col-sm-3">
            <InputLabel children="Created date"></InputLabel>
            <Input name="createdDate" placeholder='2000-10-10'/>
            </div>
            <div className="col-sm-3">
            <InputLabel children="Service url"></InputLabel>
            <Input name="serviceUrl" placeholder='http://localhost/service'/>
            </div>

            <div className="col-sm-12 search-button-container">
            <Button type="submit"
                    id="search-button"
                    className="btn btn-primary"
                    onClick={() =>
                        getServices()
                    }
                    >
                    Search
            </Button>
            <AddModal serviceList={getServiceList}></AddModal>
            </div>
        </div>
        <ListServices services={rows} getServiceList={getServiceList}></ListServices>
    </div>
  );
};
