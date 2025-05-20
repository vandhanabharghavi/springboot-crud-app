import React, {useEffect,useState} from 'react';
import axios from 'axios';

const EmployeeList = () =>
{
    const [employees, setEmployees] = useState([]);
    useEffect(()=>{
        axios.get('http://localhost:8080/api/employees')
        .then(response=>{
            setEmployees(response.data);
        }).catch(error=>{
            console.error('Error fetching Employees:',error);
        });
    },[]);
    return (
        <div>
            <h2>Employees</h2>
            {employees.length===0?(
                <p>No employees found.</p>
            ):(
                <table border="1">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Department</th>
                        </tr>
                    </thead>
                    <tbody>
                        {employees.map(emp=>(
                            <tr key={emp.id}>
                                <td>{emp.id}</td>
                                <td>{emp.name}</td>
                                <td>{emp.department}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
    );
};
export default EmployeeList;