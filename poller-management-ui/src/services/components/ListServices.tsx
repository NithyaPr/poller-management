/** @jsxImportSource @emotion/react */
import React, { FC} from 'react';
import { GetServicesResponse} from '../api/services';
import { DeleteModal } from './DeleteModal';
import { EditModal } from './EditModal';


import { TableContainer, Table, TableBody, TableHead, TableRow, TableCell, Paper, InputLabel } from '@mui/material';

interface ListServicesInput {
  services: Array<GetServicesResponse>
  getServiceList: () => void
}
export const ListServices: FC<ListServicesInput> = ({services, getServiceList}) => {
  
  return (<div className='center-div-2'>
    <div className='padding-10'><InputLabel children="Services" color='secondary'></InputLabel></div>

    <TableContainer sx={{ minWidth: 650, maxWidth: 1650 }} component={Paper}>
      <Table sx={{ minWidth: 650, maxWidth: 1600 }} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>Service Id</TableCell>
            <TableCell align="right">Service Name</TableCell>
            <TableCell align="right">Service Url</TableCell>
            <TableCell align="right">Status</TableCell>
            <TableCell align="right">Created By</TableCell>
            <TableCell align="right">DELETE</TableCell>
            <TableCell align="right">EDIT</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {services.map((row) => (
            <TableRow
              key={row.id}
              sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
            >
              <TableCell component="th" scope="row">
                {row.id}
              </TableCell>
              <TableCell align="right">{row.serviceName}</TableCell>
              <TableCell align="right">{row.serviceUrl}</TableCell>
              <TableCell align="right">{row.status}</TableCell>
              <TableCell align="right">{row.createdBy}</TableCell>
              <TableCell align="right"><DeleteModal key={'delete-' + row.id} id={row.id} serviceList={getServiceList}></DeleteModal></TableCell>
              <TableCell align="right">
                <EditModal key={'edit-' + row.id} id={row.id} name={row.serviceName} url={row.serviceUrl} serviceList={getServiceList} ></EditModal>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
    </div>
  );
}
